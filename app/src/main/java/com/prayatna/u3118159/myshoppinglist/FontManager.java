package com.prayatna.u3118159.myshoppinglist;

import android.content.res.AssetManager;
import android.graphics.Typeface;

public enum FontManager {
    FONTAWESOME("fonts/fontawesome-webfont.ttf"),
    AQUA("fonts/aqua.ttf"),
    REFUGE("fonts/Refuge.otf");

    private final String font;

    private Typeface typeface;

    private FontManager(String font) {
        this.font = font;
    }

    public Typeface typeface(AssetManager asset) {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(asset, font);
        }
        return typeface;
    }
}
