package com.example.earthquakes.tests.usecases

import com.example.earthquakes.data.QuakeLocalRepository
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.setup.UnitTestSetup
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class UseCaseGetLocalQuakeTest : UnitTestSetup() {

    @Mock
    private lateinit var mockRepository: QuakeLocalRepository

    private lateinit var mockItem: Quake
    private lateinit var mockItemId: String
    private lateinit var subject: UseCaseGetLocalQuake

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        setupMocks()
        subject = UseCaseGetLocalQuake(mockRepository)
    }

    private fun setupMocks() {
        mockItem = mockProvider.getMockQuake()
        mockItemId = mockItem.id
        runBlocking {
            Mockito.`when`(mockRepository.getQuake(mockItemId)).thenReturn(mockItem)
        }
    }

    @Test
    fun useCaseInvoked_repositoryCallsExpectedMethodWithExpectedArgAndResult() =
        runBlocking {
            // when
            val item = subject.invoke(mockItemId)

            // then
            verify(mockRepository, times(1)).getQuake(mockItemId)
            Assert.assertEquals(mockItem.id, item!!.id)
        }

}