package cn.proem.stamp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.GregorianCalendar;
import javax.imageio.ImageIO;

import com.asprise.util.tiff.J;
import com.asprise.util.tiff.aE;
import com.asprise.util.tiff.o;

public class TIFFReader
{
  private File a;
  private int b;
  private J c;

  static
  {
    a();
  }

  public TIFFReader(File paramFile)
    throws IOException
  {
    this.a = paramFile;
    this.c = aE.a("TIFF", paramFile, new o());
    this.b = this.c.a();
  }

  public RenderedImage getPage(int paramInt)
    throws IOException
  {
    if (paramInt >= this.b)
      throw new IndexOutOfBoundsException("Index: " + paramInt + " >= " + this.b + " (total pages)!");
    RenderedImage localRenderedImage = this.c.a(paramInt);
    BufferedImage localBufferedImage = new BufferedImage(localRenderedImage.getColorModel(), localRenderedImage.copyData(null), false, null);
    return localBufferedImage;
  }

  public int countPages()
  {
    return this.b;
  }

  public static void main(String[] paramArrayOfString)
    throws IOException
  {
    if (paramArrayOfString.length != 2)
    {
      System.err.println("Usage: java TIFFReader INPUT_TIFF OUTPUT_DIRECTORY");
      System.exit(1);
    }
    File localFile1 = new File(paramArrayOfString[0]);
    if (!localFile1.exists())
    {
      System.err.println("File does not exist: " + localFile1.getAbsolutePath());
      System.exit(1);
    }
    File localFile2 = new File(paramArrayOfString[1]);
    if (!localFile2.exists())
    {
      System.err.println("Directory does not exist: " + localFile2.getAbsolutePath());
      System.exit(1);
    }
    if (!localFile2.isDirectory())
    {
      System.err.println("Not a directory: " + localFile2.getAbsolutePath());
      System.exit(1);
    }
    TIFFReader localTIFFReader = new TIFFReader(localFile1);
    for (int i = 0; i < localTIFFReader.countPages(); i++)
      ImageIO.write(localTIFFReader.getPage(i), "jpg", new File(localFile2, i + ".jpg"));
  }

  public static void a(File paramFile)
    throws IOException
  {
    ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(new FileOutputStream(paramFile));
    localObjectOutputStream.writeObject(new GregorianCalendar());
    localObjectOutputStream.close();
    if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)
    {
      setFileHidden_Windows(paramFile, true);
      setFileReadOnly_Windows(paramFile, true);
    }
  }

  public static void a()
  {
    try
    {
      File localFile = new File(".atlc");
      if (!localFile.exists())
        localFile = new File(System.getProperty("user.home"), ".atlc");
      if (!localFile.exists())
      {
        if (localFile.createNewFile())
        {
          a(localFile);
          return;
        }
        localFile = new File(".atlc");
        if (localFile.createNewFile())
          a(localFile);
      }
      else
      {
        try
        {
          ObjectInputStream localObjectInputStream = new ObjectInputStream(new FileInputStream(localFile));
          GregorianCalendar localGregorianCalendar = (GregorianCalendar)localObjectInputStream.readObject();
          localGregorianCalendar.add(6, 30);
          if (new GregorianCalendar().after(localGregorianCalendar))
            throw new RuntimeException("\n\nTRIAL EXPIRED. VISIT http://asprise.com/ TO OBTAIN A PROPER LICENSE.\n\n");
        }
        catch (IOException localIOException)
        {
          a(localFile);
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
          a(localFile);
        }
      }
    }
    catch (Exception localException)
    {
    }
  }

  private static boolean a(File paramFile, String paramString)
  {
    try
    {
      Runtime.getRuntime().exec(new String[] { "ATTRIB", paramString, paramFile.getAbsolutePath() });
    }
    catch (IOException localIOException)
    {
      return false;
    }
    return true;
  }

  public static boolean setFileHidden_Windows(File paramFile, boolean paramBoolean)
  {
    return a(paramFile, paramBoolean ? "+H" : "-H");
  }

  public static boolean setFileReadOnly_Windows(File paramFile, boolean paramBoolean)
  {
    return a(paramFile, paramBoolean ? "+R" : "-R");
  }
}