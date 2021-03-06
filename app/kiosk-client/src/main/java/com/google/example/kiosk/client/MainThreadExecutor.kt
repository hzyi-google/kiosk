/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.example.kiosk.client

import android.os.Handler
import java.util.concurrent.Executor
import android.os.Looper
import com.google.kgax.grpc.Callback
import com.google.kgax.grpc.FutureCall
import com.google.kgax.grpc.on

/** Executor for the main thread */
object MainThreadExecutor : Executor {
    private val handler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        handler.post(command)
    }
}

typealias MainThread = MainThreadExecutor

/** extension to make jumping back to the UI more concise */
fun <T> FutureCall<T>.onUI(callback: Callback<T>.() -> Unit) = this.on(MainThread, callback)
