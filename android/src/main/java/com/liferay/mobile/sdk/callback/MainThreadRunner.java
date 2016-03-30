/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.mobile.sdk.callback;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;

/**
 * @author Bruno Farache
 */
public abstract class MainThreadRunner {

	public static void handler(Handler handler) {
		MainThreadRunner.handler = handler;
	}

	public static void run(Runnable runnable) {
		if (handler == null) {
			runnable.run();
			return;
		}

		handler.post(runnable);
	}

	protected static Handler handler;

	static {
		try {
			Class.forName("android.os.Build");

			if (Build.VERSION.SDK_INT != 0) {
				handler = new Handler(Looper.getMainLooper());
			}
		}
		catch (ClassNotFoundException cnfe) {
		}
	}

}