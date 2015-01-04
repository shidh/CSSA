/*
 * <ul class="pagination">
	  <li><a href="javascript:void(0)" onclick="last()">&laquo;</a></li>
	  <li class="p"><a class="number" href="page?size=10&page=1">1</a></li>
	  <li class="p"><a class="number" href="page?size=10&page=2">2</a></li>
	  <li class="p"><a class="number" href="page?size=10&page=3">3</a></li>
	  <li class="p"><a class="number" href="page?size=10&page=4">4</a></li>
	  <li class="p"><a class="number" href="page?size=10&page=5">5</a></li>
	  <li><a href="javascript:void(0)" onclick="next()">&raquo;</a></li>
	</ul>
 * 
 */

var pagination=(function(){
	return {
		/**
		 * id:the id of an empty div
		 * properties:{
		 * 	controller:string,  
		 * 	pageTotal:number,
		 * 	pageEach:number      // shows how much page in the same time 
		 * 	page:number,
		 * 	size:number
		 * }
		 */
		"create":function(id,properties){
			// create ul
			var ul=document.createElement("UL");
			ul.className="pagination";
			
			// next pages button onclick
			var currentPagesIndex=[];
			
			function removeAll(){
				// clear ul
				while(ul.firstChild){
					ul.removeChild(ul.firstChild);
				}
			}
			
			function prev(){
				// insert li
				var m=0;
				for(var j=currentPagesIndex[0]-properties.pageEach;m<properties.pageEach;j++){
					ul.appendChild(liArray[j]);
					currentPagesIndex[m]=j;
					m+=1;
				}
			}
			
			function appendPreBtn(){
				var pre=document.createElement("LI");
				var a=document.createElement("A");
				a.innerHTML="&laquo;";
				a.href="javascript:void(0)";
				a.onclick=prevOnclick;
				pre.appendChild(a);
				ul.appendChild(pre);
			}
			
			function prevOnclick(){
				if((currentPagesIndex[0]-properties.pageEach)<0){
					return;
				}
				removeAll();
				appendPreBtn();
				prev();
				appendNxtBtn();
			}
			
			function next(){
				// insert li
				var m=0;
				for(var j=currentPagesIndex[0]+properties.pageEach;m<properties.pageEach&&j<=(currentPagesIndex[properties.pageEach-1]+properties.pageEach)&&j<properties.pageTotal;j++){
					ul.appendChild(liArray[j]);
					currentPagesIndex[m]=j;
					m+=1;
				}
			}
			
			function appendNxtBtn(){
				var nxt=document.createElement("LI");
				var a=document.createElement("A");
				a.innerHTML="&raquo;";
				a.href="javascript:void(0)";
				a.onclick=nextOnclick;
				nxt.appendChild(a);
				ul.appendChild(nxt);
			}
			
			function nextOnclick(){
				if((currentPagesIndex[0]+properties.pageEach+1)>properties.pageTotal){
					return;
				}
				removeAll();
				appendPreBtn();
				next();
				appendNxtBtn();
			}
			
			// create li
			var liArray=[];
			for(var i=1;i<=properties.pageTotal;i++){
				var li=document.createElement("LI");
				var a=document.createElement("A");
				a.innerHTML=i;
				a.href=properties.controller+'?'+'size='+properties.size+'&page='+i;
				li.appendChild(a);
				liArray.push(li);
			}
			// mark selected page
			liArray[properties.page-1].className+="active";
			
			appendPreBtn();
			var firstNumber = properties.page - (properties.page -1) % properties.pageEach;
			var m=0;
			for(var j=firstNumber-1;m<properties.pageEach&&j<properties.pageTotal;j++){
				ul.appendChild(liArray[j]);
				currentPagesIndex.push(j);
				m++;
			}
			appendNxtBtn();
		
			
			document.getElementById(id).appendChild(ul);
		}
	};
}());