var Widgetschart=function(){var t=function(t,a,n,e,r,i,o,d){if("undefined"!=typeof d3){if(t){for(var l=d3.select(t),s={top:0,right:0,bottom:0,left:0},c=l.node().getBoundingClientRect().width-s.left-s.right,u=e-s.top-s.bottom,f=[],p=0;p<n;p++)f.push(Math.floor(Math.random()*n)+5);var h=d3.scale.linear().range([0,c]),g=d3.scale.linear().range([u-5,5]);h.domain([1,n-3]),g.domain([0,n]);var m=d3.svg.line().interpolate(r).x(function(t,a){return h(a)}).y(function(t,a){return g(t)}),y=d3.svg.area().interpolate(r).x(function(t,a){return h(a)}).y0(u).y1(function(t){return g(t)}),v=l.append("svg"),w=v.attr("width",c+s.left+s.right).attr("height",u+s.top+s.bottom).append("g").attr("transform","translate("+s.left+","+s.top+")"),b=w.append("defs").append("clipPath").attr("id",function(a,n){return"load-clip-"+t.substring(1)}).append("rect").attr("class","load-clip").attr("width",0).attr("height",u);b.transition().duration(1e3).ease("linear").attr("width",c);var M=w.append("g").attr("clip-path",function(a,n){return"url(#load-clip-"+t.substring(1)+")"}).append("path").datum(f).attr("transform","translate("+h(0)+",0)");function k(){c=l.node().getBoundingClientRect().width-s.left-s.right,v.attr("width",c+s.left+s.right),w.attr("width",c+s.left+s.right),h.range([0,c]),b.attr("width",c),w.select(".d3-line").attr("d",m),w.select(".d3-area").attr("d",y)}"area"==a?M.attr("d",y).attr("class","d3-area").style("fill",d):M.attr("d",m).attr("class","d3-line d3-line-medium").style("stroke",d),M.style("opacity",0).transition().duration(500).style("opacity",1),setInterval(function(){f.push(Math.floor(Math.random()*n)+5),f.shift(),M.attr("transform",null).transition().duration(i).ease("linear").attr("transform","translate("+h(0)+",0)"),"area"==a?M.attr("d",y).attr("class","d3-area").style("fill",d):M.attr("d",m).attr("class","d3-line d3-line-medium").style("stroke",d)},o),$(window).on("resize",k),$(document).on("click",".sidebar-control",k)}}else console.warn("Warning - d3.min.js is not loaded.")};return{init:function(){t("#sparklines_color","line",30,50,"basis",750,2e3,"rgba(255,255,255,0.75)")}}}();document.addEventListener("DOMContentLoaded",function(){Widgetschart.init()});