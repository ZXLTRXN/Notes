package com.example.notes.di

import com.example.notes.MainActivity
import com.example.notes.TestFragment
import com.example.notes.di.testing.Analytics
import com.example.notes.di.testing.AnalyticsImpl
import com.example.notes.di.testing.TestDep
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Scope

@Component(modules = [AppModule::class, AnalyticsModule::class],
    dependencies = [AppDeps::class] // для 2 способа
)
@AppScope
interface AppComponent {

    val testDep: TestDep // для provides

    fun inject(activity: MainActivity)
    fun inject(fragment: TestFragment)

    @Component.Builder
    interface Builder {
        // 1
//        @BindsInstance // позволяет поместить в граф что-нибудь извне
//        fun context(context: Context): Builder
        //

        // 2 // другой способ добавления внешних зависимостей
        fun appDeps(deps: AppDeps): Builder
        //

        fun build(): AppComponent
    }
}

@Scope
annotation class AppScope // кастомный скоуп, контролирующий время жизни

// Скоупами (любыми) нужно метить лишь те места, которые хранят состояния,
// тем, кто состояние не хранят незачем висеть в памяти.
// Для объектов без состояния(или немутабельных) можно сделать оптимизацию без скоупа, используя @Reusable
// она позволяет какое-то время хранить зависимости в памяти,
// чтобы отдавать одну и ту же, но это НЕ гарантируется строго, имей в виду.


@Module
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

    @Provides
    @AQualifier
    fun provideAString(): String = "a"

    @Provides
    @Named("b")
    fun provideBString(): String = "b"

    @Provides
    fun provideDefaultString(): String = "c"

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME) // время жизни, runtime достаточно
annotation class AQualifier

@Module(includes = [AnalyticsBindModule::class])
class AnalyticsModule()

@Module
interface AnalyticsBindModule {
    @Binds
    fun bindAnalytics(analyticsImpl: AnalyticsImpl): Analytics
}