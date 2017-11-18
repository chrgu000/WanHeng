var counts=document.getElementById("counts").value;
for(var a=1;a<counts+1;a++){
var sel=document.getElementById("sel"+a);
var sels=sel.getElementsByTagName("select");
var province=allArea["province"];
var frag=document.createDocumentFragment();
for(var i in province){
	var option=document.createElement("option");
	option.value=i;
	option.innerHTML=province[i];
	frag.appendChild(option);
}
sels[0].appendChild(frag);
$("#area"+a).find("option[value='33']").attr("selected",true);
$("#area"+a).find("option[value="+$("#areA"+a).val()+"]").attr("selected",true);
firstSelChange();

sels[0].onchange=firstSelChange;
function firstSelChange(){
	selChange(sels[0],sels[1],"city"); 
	var theArea=allArea["province"][sels[0].value];
	if(theArea!="北京" && theArea!="天津" && theArea!="上海" && theArea!="重庆" && theArea!="台湾" && theArea!="香港" && theArea!="澳门")
	{
		sels[2].style.display="";
		selChange(sels[1],sels[2],"district");
	}
	else
	{
		sels[2].innerHTML="";
		sels[2].style.display="none";
	}
}

sels[1].onchange=function(){
	selChange(sels[1],sels[2],"district");
}

function selChange(firstObj,secondObj,area){
	var val=firstObj.value;
	var jsonArea=allArea[area][val];
	var frag=document.createDocumentFragment();
	secondObj.innerHTML="";
	for(var i in jsonArea)
	{
		var option=document.createElement("option");
		option.value=i;
		option.innerHTML=jsonArea[i];
		frag.appendChild(option);
	}
	secondObj.appendChild(frag);
	if(area=="city")
	$("#shi"+a).find("option[value="+$("#shI"+a).val()+"]").attr("selected",true);
	if(area=="district")
	$("#xian"+a).find("option[value="+$("#xiaN"+a).val()+"]").attr("selected",true);
}
}