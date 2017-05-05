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
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * AACustomFont class for font binding and setting the typeface programmatically.
 *
 * Created by muhammad.ahsan on 04/05/2017 in AACustomFont.
 * See more at https://github.com/EngrAhsanAli
 * Copyright (C) 2017 AA-Creations.
 */
public class AACustomFont {

    @SuppressLint("StaticFieldLeak")
    private static AACustomFont instance;
    private static String TAG = "AACustomFont";
    private static final String fontsDir = "fonts";
    private static Map<String, Typeface> typefaces = new HashMap<>();
    private static Map<String, String> fonts = new HashMap<>();
    private Context mContext;

    /**
     *
     * Constructor with given context to get ready the fonts typefaces when app initializes.
     *
     * @param context Current view context
     */
    private AACustomFont(Context context) {
        mContext = context;
        getFonts();
    }

    /**
     *
     * This method will get the font resources from assets/fonts directory.
     * The font file maybe of .ttf or .otf
     * The font file name will automatically consider the alias in lowercase.
     *
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
     *
     * This method has a static instance to use in app anywhere
     * It will initialize with the application's context
     *
     * @param context current context of view
     * @return
     */
    public static AACustomFont getInstance(Context context) {
        if (instance == null) {
            instance = new AACustomFont(context.getApplicationContext());
        }
        return instance;
    }

    /**
     *
     * This method will set the alias to the font filename
     * This can be used when the app initializes for once
     *
     * @param name new font name string
     * @param fontFilename font file name string
     */
    public void setAlias(String name, String fontFilename) {
        fonts.put(name, fontFilename);
    }

    /**
     *
     * @param fontName font name string to get the Typeface
     * @return Typeface of given font
     */
    Typeface get(String fontName) {
        String fontFilename = fonts.get(fontName);
        if (fontFilename == null) {
            Log.e(TAG, "AACustomFont - Couldn't find font file: " + fontName);
            return null;
        }
        if (typefaces.containsKey(fontFilename)) {
            return typefaces.get(fontFilename);
        } else {
            Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), fontsDir + "/" + fontFilename);
            typefaces.put(fontFilename, typeface);
            return typeface;
        }
    }
}
