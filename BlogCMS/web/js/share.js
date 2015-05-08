/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function (){
                                        //document.getElementById("share-mail").href = "mailto:?to=&subject=Blog%20CMS&body=" + document.location;
    $("#share-mail").attr("href", "mailto:?to=&subject=Blog%20CMS&body="+document.location);
    $("#share-facebook").attr("onclick", "return windowpop('http://www.facebook.com/sharer.php?u="+document.location+"', 545, 433)");
    $("#share-twitter").attr("onclick", "return windowpop('http://twitter.com/share?text=Blog%20CMS%20Link&url="+document.location+"', 545, 433)");
    
});

function windowpop(url, width, height) {
    var leftPosition, topPosition;
    //Allow for borders.
    leftPosition = (window.screen.width / 2) - ((width / 2) + 10);
    //Allow for title and status bars.
    topPosition = (window.screen.height / 2) - ((height / 2) + 50);
    //Open the window.
    window.open(url, "Window2", "status=no,height=" + height + ",width=" + width + ",resizable=yes,left=" + leftPosition + ",top=" + topPosition + ",screenX=" + leftPosition + ",screenY=" + topPosition + ",toolbar=no,menubar=no,scrollbars=no,location=no,directories=no");
}
