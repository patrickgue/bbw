class MediaElement {
    constructor(title, text, picture, license) {
        this.title = title;
        this.text = text;
        this.picture = picture;
        this.attr = [];
        this.license = license;
    }

    addAttr(desc, value) {
        this.attr.push({
            "descr": desc,
            "value": value
        });
    }
}

class PictureElement extends MediaElement {
    constructor(title, text, picture, license) {
        super(title, text, picture, license);
    }
}

class AudioElement extends MediaElement {
    constructor(title, text, picture, uri, license) {
        super(title, text, picture, license);
        this.uri = uri;
    }
}


class VideoElement extends MediaElement {
    constructor(title, text, uri, license) {
        super(title, text, undefined, license);
        this.uri = uri;
    }
}
