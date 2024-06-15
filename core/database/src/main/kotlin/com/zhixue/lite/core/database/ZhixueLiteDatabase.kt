package com.zhixue.lite.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zhixue.lite.core.database.dao.PaperInfoDao
import com.zhixue.lite.core.database.dao.RemotePageDao
import com.zhixue.lite.core.database.dao.ReportDao
import com.zhixue.lite.core.database.dao.SheetInfoDao
import com.zhixue.lite.core.database.dao.TrendInfoDao
import com.zhixue.lite.core.database.dao.UserInfoDao
import com.zhixue.lite.core.database.model.PaperInfoEntity
import com.zhixue.lite.core.database.model.RemotePageEntity
import com.zhixue.lite.core.database.model.ReportInfoEntity
import com.zhixue.lite.core.database.model.SheetInfoEntity
import com.zhixue.lite.core.database.model.TrendInfoEntity
import com.zhixue.lite.core.database.model.UserInfoEntity
import com.zhixue.lite.core.database.util.SheetPageListConverter

@Database(
    version = 2,
    entities = [
        UserInfoEntity::class,
        RemotePageEntity::class,
        ReportInfoEntity::class,
        PaperInfoEntity::class,
        TrendInfoEntity::class,
        SheetInfoEntity::class
    ]
)
@TypeConverters(SheetPageListConverter::class)
internal abstract class ZhixueLiteDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao
    abstract fun remotePageDao(): RemotePageDao
    abstract fun reportDao(): ReportDao
    abstract fun paperInfoDao(): PaperInfoDao
    abstract fun trendInfoDao(): TrendInfoDao
    abstract fun sheetInfoDao(): SheetInfoDao
}