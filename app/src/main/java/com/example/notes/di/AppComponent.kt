package com.example.notes.di

import android.content.Context
import com.example.notes.MainActivity
import com.example.notes.TestFragment
import com.example.notes.di.testing.TestDep
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Scope

@Scope
annotation class AppScope // кастомный скоуп, контролирующий время жизни

// Скоупами (любыми) нужно метить лишь те места, которые хранят состояния,
// а тем, кто состояние не хранят незачем висеть в памяти.
// Для объектов без состояния(или немутабельных) можно сделать оптимизацию специальным скоупом @Reusable
// он позволяет какое-то время хранить зависимости в памяти,
// чтобы отдавать одну и ту же, но это НЕ гарантируется строго, имей в виду.

@Component(
    modules = [AppModule::class, AnalyticsModule::class],
    dependencies = [AppDeps::class] // для 2 способа
)
@AppScope
interface AppComponent: FeatureDeps {
    override fun context(): Context

    val testDep: TestDep // указание тут нужно лишь для тех, что используют provides

    fun inject(activity: MainActivity)
    fun inject(fragment: TestFragment)

    @Component.Builder
    interface Builder {
        // 1
        // @BindsInstance // позволяет поместить в граф что-нибудь извне
        // fun context(context: Context): Builder
        //

        // 2 // другой способ добавления внешних зависимостей
        fun appDeps(deps: AppDeps): Builder
        //

        fun build(): AppComponent
    }
}

@Module(includes = [TestStringsModule::class])
class AppModule {
    @Provides
    fun provideTestDep(
        @AQualifier
        a: String,
        @Named("b")
        b: String,
        c: String
    ): TestDep {
        return TestDep(a, b, c)
    }
}