package com.surpassli.common.imageloader

class ImageLoaderConfig {
    //图片缓存对象
    var bitmapCache: ImageCache = MemoryCache()

    //loading和加载失败对象
    var displayConfig = DisplayConfig()

    //加载策略
    var loadStrategy: LoadStrategy = SerialStrategy()

    //线程数量
    var threadCount = Runtime.getRuntime().availableProcessors() + 1

    private constructor()

    companion object {
        class Builder {
            var bitmapCache: ImageCache = MemoryCache()
            var displayConfig = DisplayConfig()
            var loadStrategy: LoadStrategy = SerialStrategy()
            var threadCount = Runtime.getRuntime().availableProcessors() + 1

            fun setThreadCount(count: Int): Builder {
                threadCount = count
                return this
            }

            fun setCache(cache: ImageCache): Builder {
                bitmapCache = cache
                return this
            }

            fun setLoadingPlaceholder(resId: Int): Builder {
                displayConfig.loadingResId = resId
                return this
            }

            fun setNotFoundPlaceHolder(resId: Int): Builder {
                displayConfig.failedResId = resId
                return this
            }

            fun setLoadPolicy(strategy: LoadStrategy?): Builder {
                if (strategy != null) {
                    loadStrategy = strategy
                }
                return this
            }

            private fun applyConfig(config: ImageLoaderConfig) {
                config.bitmapCache = this.bitmapCache
                config.displayConfig = this.displayConfig
                config.loadStrategy = this.loadStrategy
                config.threadCount = this.threadCount
            }

            fun create(): ImageLoaderConfig {
                val config = ImageLoaderConfig()
                applyConfig(config)
                return config
            }
        }
    }
}