package com.judelakshitha.arimacnewsapp.ui.base

import androidx.lifecycle.ViewModel
import java.util.logging.ErrorManager

abstract class BaseViewModel:ViewModel() {
    lateinit var errorManager: ErrorManager

}