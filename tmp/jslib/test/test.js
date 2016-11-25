main(["d","repeat"],function(d,repeat) {

    d("#error").style("display","none");
    d("#time")
	.style("fontSize","72pt")
	.style("color","#222")
	.style("fontWeight","600")
	.style("fontFamily","Helvetica");
    repeat.start(function() {
	let dt = new Date();
	let dateString =
	    (dt.getHours() < 10 ? "0" : "")+dt.getHours() + ":" +
	    (dt.getMinutes() < 10 ? "0" : "" ) + dt.getMinutes() + ":" +
	    (dt.getSeconds() < 10 ? "0" : "") + dt.getSeconds();
	d("#time").content("Time: "+dateString);
    }, 500);
    d("p.eachdemo").each(function(obj,i,l) {
	obj.content((i + 1) + " of " + l);
    });
});
