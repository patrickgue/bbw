/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function (){
                                        //document.getElementById("share-mail").href = "mailto:?to=&subject=Blog%20CMS&body=" + document.location;
    $("#share-mail").attr("href", "mailto:?to=&subject=Blog%20CMS&body="+document.location);
    $("#share-facebook").attr("href", "http://www.facebook.com/sharer.php?u="+document.location);
    $("#share-twitter").attr("href", "http://twitter.com/share?text=Blog%20CMS%20Link&url="+document.location);
    
});
