package com.cinevault.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @HiltAndroidApp triggers Hilt's code generation and creates the
 * app-level dependency container that every @AndroidEntryPoint
 * (activities, fragments) and @HiltViewModel hangs off of.
 */
@HiltAndroidApp
class CineVaultApp : Application()
