package com.hci.koinmvvmcoroutineflow

import android.app.Application
import androidx.lifecycle.LiveData
import com.hci.koinmvvmcoroutineflow.di.AppTestModules
import com.hci.koinmvvmcoroutineflow.livedata.LiveDataTestObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
internal class ViewModelTest : KoinTest {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var context: Application

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup(){
        startKoin{
            androidContext(context)
            modules(AppTestModules.modules)
        }
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown(){
        stopKoin()
        Dispatchers.resetMain() // 메인 디스패쳐를 초기화 해줘야 메모리 누수가 없음
    }

    protected fun <T> LiveData<T>.test(): LiveDataTestObserver<T>{
        val testObserver = LiveDataTestObserver<T>()
        observeForever(testObserver)
        return testObserver
    }
}