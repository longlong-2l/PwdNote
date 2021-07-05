package com.surpassli.login.data.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.surpassli.login.BR

class User : BaseObservable() {
    var id = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.id)
        }
        @Bindable
        get
    var name = ""
}