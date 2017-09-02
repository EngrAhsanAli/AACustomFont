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

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * A helper class for font binding on current view's context.
 * <p>
 * Created by muhammad.ahsan on 04/05/2017 in AACustomFont.
 * See more at https://github.com/EngrAhsanAli
 * Copyright (C) 2017 AA-Creations.
 */

@SuppressWarnings("unused")
public class AAHelper {

    /**
     * Binding Adapter for setting the typeface for the given font name.
     * The font file should be placed in assets folder in the directory of fonts.
     * Any view that has parent class of TextView can easily use this method.
     *
     * @param view     Any TextView expected for setting the typeface
     * @param fontName Font file name
     */
    @BindingAdapter({"bind:font"})
    public static void setFont(View view, String fontName) {
        AACustomFont.setCustomFont(view, fontName);
    }


}