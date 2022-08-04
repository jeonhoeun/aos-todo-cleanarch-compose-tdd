package com.riyusoft.feature.main

// todo loading에서 변경되는것을 기다리는 방식을 찾아야됨
// internal class MainScreenKtTest {
//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun `MainScreen-empty-screen`() = runTest(
//        dispatchTimeoutMs = 2000L
//    ) {
//        val repo: TodoRepository = object : TodoRepository {
//            override fun getTodosStream(): Flow<List<com.riyusoft.todo.core.model.Todo>> {
//                println("getTodosCalled")
//                return emptyList<List<Todo>>().asFlow()
//            }
//        }
//        val viewModel = MainViewModel(repo)
//
//        composeTestRule.setContent {
//            MainRoute(viewModel)
//        }
//
//        delay(1000L)
//
//        with(composeTestRule) {
//            onNodeWithTag("empty_desc_text").assertIsDisplayed().assertTextEquals("데이터를\n추가해주세요")
//            onNodeWithTag("new_button").assertIsDisplayed()
//        }
//    }
// }
