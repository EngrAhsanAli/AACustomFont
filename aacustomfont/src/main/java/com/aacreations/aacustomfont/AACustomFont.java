/*
 * Copyright (C) 2017 AA-Creations.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aacreations.aacustomfont;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * AACustomFont class for font binding and setting the typeface programmatically.
 * <p>
 * Created by muhammad.ahsan on 04/05/2017 in AACustomFont.
 * See more at https://github.com/EngrAhsanAli
 * Copyright (C) 2017 AA-Creations.
 */
public class AACustomFont {

    @SuppressLint("StaticFieldLeak")
    private static AACustomFont instance;
    private static String TAG = "AACustomFont";
    private static final String fontsDir = "fonts";
    private String defaultFontName;
    private static Map<String, Typeface> typefaces = new HashMap<>();
    private static Map<String, String> fonts = new HashMap<>();
    private Context mContext;

    /**
     * Constructor with given context to get ready the fonts typefaces when app initializes.
     *
     * @param context Current view context
     */
    private AACustomFont(Context context) {
        mContext = context;
        getFonts();
    }

    /**
     * This method will get the font resources from assets/fonts directory.
     * The font file maybe of .ttf or .otf
     * The font file name will automatically consider the alias in lowercase.
     */
    private void getFonts() {
        AssetManager am = mContext.getResources().getAssets();
        String fileList[];
        try {
            fileList = am.list(fontsDir);
        } catch (IOException e) {
            Log.e(TAG, "AACustomFont - Error loading fonts from assets/fonts.");
            return;
        }

        for (String filename : fileList) {
            String alias = filename.substring(0, filename.lastIndexOf('.'));
            fonts.put(alias, filename);
            fonts.put(alias.toLowerCase(), filename);
        }
    }

    /**
     * Setter method for default font name
     *
     * @param defaultFontName font file name or font alias
     * @return Instance
     */
    public AACustomFont setDefaultFontName(String defaultFontName) {
        this.defaultFontName = defaultFontName;
        return this;
    }

    /**
     * This method has a static instance to use in app anywhere
     * It will initialize with the application's context
     *
     * @param context current context of view
     * @return Instance
     */
    public static AACustomFont getInstance(Context context) {
        if (instance == null) {
            instance = new AACustomFont(context.getApplicationContext());
        }
        return instance;
    }

    /**
     * This method will set the alias to the font filename
     * This can be used when the app initializes for once
     *
     * @param name         new font name string
     * @param fontFilename font file name string
     * @return Instance
     */
    public AACustomFont setAlias(String name, String fontFilename) {
        fonts.put(name, fontFilename);
        return this;
    }

    /**
     * Gets the font with alias or font file name
     * Checks in cache first for existing typefaces
     *
     * @param fontName font alias or font file name
     * @return font name
     */
    private String getFont(String fontName) {
        String fontFilename = fonts.get(fontName);
        if (fontFilename == null) {
            fontFilename = fonts.get(defaultFontName);
            if (fontFilename == null) {
                if (typefaces.containsKey(defaultFontName))
                    return defaultFontName;
                else {
                    Log.e(TAG, "AACustomFont - Couldn't find font file: " + fontName);
                    return null;
                }
            }
        }
        return fontFilename;
    }

    /**
     * @param fontName font name string to get the Typeface
     * @return Typeface of given font
     */
    private Typeface get(String fontName) {
        String fontFilename = getFont(fontName);
        if (fontFilename == null)
            return null;
        if (typefaces.containsKey(fontFilename))
            return typefaces.get(fontFilename);
        else {
            Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), fontsDir + "/" + fontFilename);
            typefaces.put(fontFilename, typeface);
            return typeface;
        }
    }

    /**
     * Static method for setting the typeface for the given View
     * The font file should be placed in assets folder in the directory of fonts.
     * Any view that has parent class of TextView can easily use this method.
     *
     * @param view     typeface view
     * @param fontName font name
     */
    public static void setCustomFont(View view, String fontName) {
        if (view instanceof TextView) {
            Context context = view.getContext();
            Typeface font = getInstance(context).get(fontName);
            TextView textView = (TextView) view;
            if (textView.getTypeface() != font)
                textView.setTypeface(font);
        }
    }

    /**
     * Override specific font in current Activity
     * For all the widgets that has base class of TextView
     * Font name is optional
     * Default font name is used if set
     *
     * @param context Activity Context
     * @param fontName font name
     */
    public static void overrideFonts(Context context, String fontName) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
            overrideFonts(view, fontName);
        }
    }

    /**
     * Override specific font in current Activity
     * With default font name
     *
     * @param context Activity Context
     * @return Instance
     */
    public AACustomFont overrideFonts(Context context) {
        overrideFonts(context, defaultFontName);
        return this;
    }

    /**
     * Override specific font in current View
     * All the subviews automatically set as parent view
     *
     * @param view specific view
     * @param fontName font name
     */
    public static void overrideFonts(View view, String fontName) {
        try {
            if (view instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) view;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(child, fontName);
                }
            } else
                setCustomFont(view, fontName);
        } catch (Exception e) {
            Log.e(TAG, "AACustomFont - Couldn't find font file: " + fontName);
        }
    }
}
