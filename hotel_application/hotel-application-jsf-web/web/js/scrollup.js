/* 
 * Copyright (C) 2016 guenthard
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


(function(){
    var scrollUpButton = document.getElementById("scroll-up-button");
    var doc = document.documentElement;
    scrollUpButton.style.display = "none";
    window.onscroll = function(){
        
        var top = (window.pageYOffset || doc.scrollTop)  - (doc.clientTop || 0);
        
        if(top > window.innerHeight && scrollUpButton.style.display === "none"){
            scrollUpButton.style.display = "block";
        } else if(top < window.innerHeight && scrollUpButton.style.display === "block") {
            scrollUpButton.style.display = "none";
        }
    };
    
    scrollUpButton.onclick = function(){
        window.scrollTo(0,0);
    };
})();