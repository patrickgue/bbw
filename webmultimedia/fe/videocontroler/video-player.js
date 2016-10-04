/**
 * Created by mdsystems on 13.09.16.
 */
var videoPlayer;

document.addEventListener("DOMContentLoaded", function() { initialiseVideoPlayer(); }, false)

function initialiseVideoPlayer() {
    videoPlayer = document.getElementsByClassName('video');
    videoPlayer.controls = false;
    videoPlayer.addEventListener('timeupdate', updateProgressBar, false);
}

function togglePlayPause() {
    var btn = document.getElementById('play-pause-button');
    if (videoPlayer.paused || videoPlayer.ended) {
        btn.title = 'pause';
        btn.innerHTML = 'pause';
        btn.className = 'pause';
        videoPlayer.play();
    }
    else {
        btn.title = 'play';
        btn.innerHTML = 'play';
        btn.className = 'play';
        videoPlayer.pause();
    }
}

function changeButtonType(btn, value) {
    btn.title = value;
    btn.innerHTML = value;
    btn.className = value;
}

function stopPlayer() {
    videoPlayer.pause();
    videoPlayer.currentTime = 0;
}

function changeVolume(direction) {
    if (direction === '+') videoPlayer.volume += videoPlayer.volume == 1 ? 0 : 0.1;
    else videoPlayer.volume -= (videoPlayer.volume == 0 ? 0 : 0.1);
    videoPlayer.volume = parseFloat(videoPlayer.volume).toFixed(1);
}

function toggleMute() {
    var btn = document.getElementById('mute-button');
    if (videoPlayer.muted) {
        changeButtonType(btn, 'mute');
        videoPlayer.muted = false;
    }
    else {
        changeButtonType(btn, 'unmute');
        videoPlayer.muted = true;
    }
}

function updateProgressBar() {
    var progressBar = document.getElementById('progress-bar');
    var percentage = Math.floor((100 / videoPlayer.duration) *
        videoPlayer.currentTime);
    progressBar.value = percentage;
    progressBar.innerHTML = percentage + '% played';
}
