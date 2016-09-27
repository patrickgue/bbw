/**
 * Created by mdsystems on 13.09.16.
 */
var videoPlayer;

document.addEventListener("DOMContentLoaded", function() { initialiseVideoPlayer(); }, false)

function initialiseVideoPlayer() {
    videoPlayer = document.getElementsByClassName('video');
    videoPlayer.controls = false;git
}
