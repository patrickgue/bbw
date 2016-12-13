class MediaElement{
    constructor(title, text, picture) {
	this.title = title;
	this.text = text;
	this.picture = picture;
	this.attr = [];
    }

    addAttr(desc, value) {
	this.attr.push({
	    "descr" : desc,
	    "value" : value
	});
    }
}

class PictureElement extends MediaElement {
    constructor(title, text, picture) {
	super(title, text, picture);
    }
}

class AudioElement extends MediaElement {
    constructor(title, text, picture, uri) {
	super(title, text, picture);
	this.uri = uri;
    }
}


class VideoElement extends MediaElement {
    constructor(title, text, uri) {
	super(title, text, undefined);
	this.uri = uri;
    }
}
