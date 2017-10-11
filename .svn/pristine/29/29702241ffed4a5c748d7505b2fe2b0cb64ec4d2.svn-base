/**
 * 初始化，打开一个模板文件
 */
function intializePage(fileUrl)
{	
	alert(fileUrl);
	TANGER_OCX_OBJ.BeginOpenFromURL(fileUrl);
}




var width = "100%"; //窗口宽度
var TANGER_OCX_bDocOpen = false;
var TANGER_OCX_filename;
var TANGER_OCX_actionURL; //For auto generate form fiields
var TANGER_OCX_OBJ; //The Control
var TANGER_OCX_Username="南京普恩信息技术有限公司";

var obj_id='TANGER_OCX';
var clsid='C9BC4DFF-4248-4a3c-8A49-63A7D317F404';
var codebase = getContextPath()+'/resources/office/ntko/OfficeControl.cab#version=5,0,2,2';
var ntkowidth="100%";
var ntkoheight="530";
var BorderStyle="1";
var TitlebarColor="42768";
var TitlebarTextColor='0';
//var Caption="欢迎使用普恩系统";
var Caption="";
var ToolBars="-1";
var Menubar='-1';
var IsNoCopy='-1';
var MakerCaption="南京普恩信息技术有限公司";
var MakerKey="1A1108572E60AD73446F60ED599833F5850F6F4C"; 
//
var ProductCaption="南京普恩信息技术有限公司";
var ProductKey='D492A0EBDD4FFB1EAE746BAF3C27C45D911DA511'; 
//
var error_msg='<SPAN STYLE="color:red">不能装载文档控件。请在检查浏览器的选项中检查浏览器的安全设置。</SPAN>';
//装载ntko文档
function Load_ntko(obj_id,clsid,codebase,ntkowidth,ntkoheight,BorderStyle,TitlebarColor,TitlebarTextColor,Caption,ToolBars,Menubar,IsNoCopy,MakerCaption,MakerKey,ProductCaption,ProductKey,error_msg)
{
	alert();
	document.write('<object id="'+obj_id+'" classid="clsid:'+clsid+'" codebase="'+codebase+'" width="'+ntkowidth+'" height="'+ntkoheight+'">'); 
	document.write('<param name="BorderStyle" value="'+BorderStyle+'">  '); 
	document.write('<param name="TitlebarColor" value="'+TitlebarColor+'">  '); 
	document.write('<param name="TitlebarTextColor" value="'+TitlebarTextColor+'">  '); 
	document.write('<param name="Caption" value="'+Caption+'">  '); 
	document.write('<param name="ToolBars" value="'+ToolBars+'">  '); 
	document.write('<param name="Menubar" value="'+Menubar+'">  '); 
	document.write('<param name="IsNoCopy" value="'+IsNoCopy+'">  '); 
	//document.write('<param name="MakerCaption" value="'+MakerCaption+'">  '); 
	//document.write('<param name="MakerKey" value="'+MakerKey+'">  '); 
	document.write('<param name="ProductCaption" value="'+ProductCaption+'">  '); 
	document.write('<param name="ProductKey" value="'+ProductKey+'">  '); 
	document.write(error_msg); 
	document.write('</object>  '); 
}
//打开文件
function TANGER_OCX_OpenDoc_Gen(doc_url)
{
	//alert(doc_url);
	TANGER_OCX_OBJ = document.all.item("TANGER_OCX");
	TANGER_OCX_OBJ.BeginOpenFromURL(doc_url);
}
//创建新文件
function TANGER_OCX_CreateDoc(menuID)
{
	TANGER_OCX_OBJ = document.all.item("TANGER_OCX");
	switch(menuID)
	{
		case '1':
		TANGER_OCX_OBJ.CreateNew("Word.Document");
		break;
		case '2':
		TANGER_OCX_OBJ.CreateNew("Excel.Sheet");
		break;
		case '3':
		TANGER_OCX_OBJ.CreateNew("Powerpoint.Show");
		break;
		case '0':
		TANGER_OCX_OBJ.Close();
		break;
	}
}
//另存为新版本
function SaveNewDoc(doc_id)
{
	var edition=document.all.edition.value;
	edition=edition.Trim();
	var where = "DOC_ID='"+doc_id+"' AND EDITION='"+edition+"'";
	if(edition=="")
		alert("版本号必填");
	else if(Tool_IsExistTableFieldValue("KM_DOCVER", "EDITION", edition, where))
	{
		alert("版本号重复!");
	}	
	else
	{
		document.all.require_rework.value='Y';
		TANGER_OCX_SaveDoc_Gen();
	}	
}
//保存
function SaveDoc()
{
	TANGER_OCX_SaveDoc_Gen();
}

//确定文件
function TANGER_OCX_SaveDoc_Gen()
{
	var retStr=new String;
	var edition_des = document.all.edition_des.value;
	try
	{
	 	//调用控件的SaveToURL函数
		var retHTML = TANGER_OCX_OBJ.SaveToURL
		(
			document.forms[0].action+"&edition_des="+encodeURIComponent(edition_des),  //此处为uploadedit.asp
			"EDITFILE",	//文件输入域名称,可任选,不与其他<input type=file name=..>的name部分重复即可
			"", //可选的其他自定义数据－值对，以&分隔。如：myname=tanger&hisname=tom,一般为空
			document.forms[0].filename.value, //文件名,此处从表单输入获取，也可自定义
			"form1" //控件的智能提交功能可以允许同时提交选定的表单的所有数据.此处可使用id或者序号
		); //此函数会读取从服务器上返回的信息并确定到返回值中。
		//打开一个新窗口显示返回数据
		if(retHTML.Trim()=="1")
		{
			alert("更新的文件已成功提交!");
		}else
		{
		   alert("文件提交失败!");
		}		
		opener.parent.parent.TwoFrame.location.reload();
		window.close();
	}
	catch(err){
		alert("不能确定到URL：" + err.number + ":" + err.description);
	}
	finally{
	}
}

function TANGER_OCX_OnDocumentOpened(str, obj)
{
	TANGER_OCX_bDocOpen = true;		
	//设置用户名
	//TANGER_OCX_SetDocUser(TANGER_OCX_Username);	
}

function TANGER_OCX_OnDocumentClosed()
{
   TANGER_OCX_bDocOpen = false;
}

//切换显示修订工具栏和工具菜单（保护修订）
function TANGER_OCX_EnableReviewBar(boolvalue)
{
	if(!TANGER_OCX_bDocOpen)
	{
		return;
	}
	TANGER_OCX_OBJ.ActiveDocument.CommandBars("Reviewing").Enabled = boolvalue;
	//TANGER_OCX_OBJ.ActiveDocument.CommandBars("Track Changes").Enabled = boolvalue;
	TANGER_OCX_OBJ.IsShowToolMenu = boolvalue;	//关闭工具菜单
}
//得到公共文档的强制版本更新设置权限
function ver_init(doc_id)
{
	var params_ver=new Array();
	params_ver.push("DOC_ID=" + doc_id);
	var update_flag=Page_Redirect("com.proem.pms.km.web.KmUploadWeb","getVerUpdateFlag",params_ver);
	if(update_flag=="1")
	{
	    document.getElementById("btn_save").disabled="disabled";
	}
}
//测试打印
function TANGER_OCX_PrintDoc(isBackground)
{
	var oldOption;	
	try
	{
		var objOptions =  TANGER_OCX_OBJ.ActiveDocument.Application.Options;
		oldOption = objOptions.PrintBackground;
		objOptions.PrintBackground = isBackground;
	}
	catch(err){};
	TANGER_OCX_OBJ.printout(false);
	try
	{
		var objOptions =  TANGER_OCX_OBJ.ActiveDocument.Application.Options;
		objOptions.PrintBackground = oldOption;
	}
	catch(err){};	
}