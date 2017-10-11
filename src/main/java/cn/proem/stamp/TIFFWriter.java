
package cn.proem.stamp;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.*;
import java.util.Vector;
import javax.swing.ImageIcon;

import com.asprise.util.tiff.F;
import com.asprise.util.tiff.aE;
import com.asprise.util.tiff.aH;
import com.asprise.util.tiff.av;

public class TIFFWriter
{

    public static final int TIFF_COMPRESSION_NONE = 1;
    public static final int TIFF_COMPRESSION_PACKBITS = 32773;
    public static final int TIFF_COMPRESSION_GROUP3_1D = 2;
    public static final int TIFF_COMPRESSION_GROUP3_2D = 3;
    public static final int TIFF_COMPRESSION_GROUP4 = 4;
    public static final int TIFF_COMPRESSION_DEFLATE = 32946;
    public static final int TIFF_CONVERSION_TO_BLACK_WHITE = 1;
    public static final int TIFF_CONVERSION_TO_GRAY = 2;
    public static final int TIFF_CONVERSION_NONE = 0;
    public static boolean reverseColorDuringConversion = false;
    public static int preferredResolution = 0;
    private static final int a[] = {
        32946, 2, 3, 4, 1, 32773
    };
    private static final int b[] = {
        0, 1, 2
    };

    public TIFFWriter()
    {
    }

    public static void createTIFFFromImages(BufferedImage abufferedimage[], File file)
        throws IOException
    {
        createTIFFFromImages(abufferedimage, 1, 4, file);
    }

    public static void createTIFFFromImages(BufferedImage abufferedimage[], int i, int j, File file)
        throws IOException
    {
        if((j == 2 || j == 3 || j == 4) && i != 1)
            throw new IllegalArgumentException("You have specified a compression that only applicable to black-white images. Please specify TIFF_CONVERSION_BLACK_WHITE as the conversion method.");
        if(abufferedimage == null || abufferedimage.length == 0)
            throw new IllegalArgumentException("No input images.");
        boolean flag = false;
        for(int k = 0; k < b.length; k++)
        {
            if(b[k] != i)
                continue;
            flag = true;
            break;
        }

        if(!flag)
            throw new IllegalArgumentException("Invalid conversion.");
        flag = false;
        for(int l = 0; l < a.length; l++)
        {
            if(a[l] != j)
                continue;
            flag = true;
            break;
        }

        if(!flag)
            throw new IllegalArgumentException("Invalid compression.");
        for(int i1 = 0; i1 < abufferedimage.length; i1++)
        {
            BufferedImage bufferedimage = abufferedimage[i1];
        }

        if(i != 0)
        {
            for(int j1 = 0; j1 < abufferedimage.length; j1++)
                if(i == 1)
                    abufferedimage[j1] = a(abufferedimage[j1], reverseColorDuringConversion);
                else
                if(i == 2)
                    abufferedimage[j1] = a(abufferedimage[j1]);

        }
        FileOutputStream fileoutputstream = new FileOutputStream(file);
        F f = new F();
        f.a(j);
        if(preferredResolution > 0)
        {
            av aav[] = new av[2];
            aav[0] = new av(282, 5, 1, new long[][] {
                new long[] {
                    (long)preferredResolution, 1L
                }, new long[2]
            });
            aav[1] = new av(283, 5, 1, new long[][] {
                new long[] {
                    (long)preferredResolution, 1L
                }, new long[2]
            });
            f.a(aav);
        }
        aH ah = aE.a("TIFF", fileoutputstream, f);
        Vector vector = new Vector();
        for(int k1 = 1; k1 < abufferedimage.length; k1++)
            vector.add(abufferedimage[k1]);

        f.a(vector.iterator());
        ah.a(abufferedimage[0]);
        fileoutputstream.close();
    }

    private static BufferedImage a(BufferedImage bufferedimage, boolean flag)
    {
        byte abyte0[] = {
            -1, 0
        };
        IndexColorModel indexcolormodel = new IndexColorModel(1, 2, abyte0, abyte0, abyte0);
        java.awt.image.WritableRaster writableraster = indexcolormodel.createCompatibleWritableRaster(bufferedimage.getWidth(), bufferedimage.getHeight());
        BufferedImage bufferedimage1 = new BufferedImage(indexcolormodel, writableraster, false, null);
        for(int i = 0; i < bufferedimage.getWidth(); i++)
        {
            for(int j = 0; j < bufferedimage.getHeight(); j++)
                bufferedimage1.setRGB(i, j, bufferedimage.getRGB(i, j));

        }

        return bufferedimage1;
    }

    private static BufferedImage a(BufferedImage bufferedimage)
    {
        BufferedImage bufferedimage1 = new BufferedImage(bufferedimage.getWidth(), bufferedimage.getHeight(), 10);
        bufferedimage1.createGraphics().drawImage(bufferedimage, 0, 0, null);
        return bufferedimage1;
    }

    public static BufferedImage getBufferedImageFromImage(Image image)
    {
        if(image == null)
        {
            return null;
        } else
        {
            Canvas canvas = new Canvas();
            new ImageIcon(image);
            int i = image.getWidth(null);
            int j = image.getHeight(null);
            BufferedImage bufferedimage = new BufferedImage(i, j, 1);
            Graphics2D graphics2d = bufferedimage.createGraphics();
            graphics2d.drawImage(image, 0, 0, null);
            return bufferedimage;
        }
    }

    public static void main(String args[])
        throws IOException
    {
        String s = "Usage: java com.asprise.util.tiff.TIFFWriter [tiff file to be created] [conversion option, set -1 to use default] [compression option, set -1 to use default] [path of image1] [path of image2] ...";
        if(args.length < 4)
            throw new IllegalArgumentException(s);
        File file = new File(args[0]);
        int i = -1;
        int j = -1;
        try
        {
            i = Integer.parseInt(args[1]);
        }
        catch(NumberFormatException numberformatexception)
        {
            throw new NumberFormatException(s + "\nInvalid image conversion option (" + args[1] + "). if you are not sure, put -1 here.");
        }
        try
        {
            j = Integer.parseInt(args[2]);
        }
        catch(NumberFormatException numberformatexception1)
        {
            throw new NumberFormatException(s + "\nInvalid TIFF compression option (" + args[2] + "). if you are not sure, put -1 here.");
        }
        BufferedImage abufferedimage[] = new BufferedImage[args.length - 3];
        for(int k = 3; k < args.length; k++)
            abufferedimage[k - 3] = getBufferedImageFromImage(Toolkit.getDefaultToolkit().createImage(args[k]));

        if(i == -1)
            i = 1;
        if(j == -1)
            j = 4;
        createTIFFFromImages(abufferedimage, i, j, file);
    }
}
