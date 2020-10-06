package com.tif.testoonjo.navigator

class Navigator private constructor() {
    //Singleton class for Navigating to other Activity/Fragment
    companion object {

        private var navigation: Navigator? = null

        fun getNavigator(): Navigator? {
            synchronized(this)
            {
                if (navigation == null) {
                    navigation = Navigator()
                }
            }
            return navigation
        }
    }
}