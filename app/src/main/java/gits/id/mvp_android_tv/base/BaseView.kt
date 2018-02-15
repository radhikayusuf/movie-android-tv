/*
 * Copyright 2016, The Android Open Source Project
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

package gits.id.mvp_android_tv.base

import android.content.Context
import android.support.v17.leanback.widget.ArrayObjectAdapter

interface BaseView<T> {

    fun context(): Context

    fun createTVComponent()

    fun provideAdapter(adapter: ArrayObjectAdapter)

    fun showMessage(msg: Int)

    fun showMessage(msg: String)
}
