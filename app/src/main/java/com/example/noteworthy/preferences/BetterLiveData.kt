package com.example.noteworthy.preferences

import androidx.lifecycle.MutableLiveData

// LiveData that doesn't accept null values
class BetterLiveData<T>(value: T) : MutableLiveData<T>(value) {

    
}