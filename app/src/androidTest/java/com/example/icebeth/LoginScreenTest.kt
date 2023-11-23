package com.example.icebeth

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.icebeth.core.data.util.ConnectivityObserver
import com.example.icebeth.core.domain.util.AuthError
import com.example.icebeth.ui.screens.login.LoginContract
import com.example.icebeth.ui.screens.login.LoginScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginScreenElementsDisplayed() {
        composeTestRule.setContent {
            LoginScreen(
                state = LoginContract.State(),
                isForbidden = false,
                onEventSent = {},
                connectState = ConnectivityObserver.Status.Available,
                snackbarHostState = SnackbarHostState(),
                loading = false
            )
        }

        composeTestRule.onNodeWithText("Добро пожаловать!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Логин").assertIsDisplayed()
        composeTestRule.onNodeWithText("Пароль").assertIsDisplayed()
        composeTestRule.onNodeWithText("ВХОД").assertIsDisplayed()
    }

    @Test
    fun loginButtonClickable() {
        var buttonClicked = false
        composeTestRule.setContent {
            LoginScreen(
                state = LoginContract.State(),
                isForbidden = false,
                onEventSent = { event ->
                    if (event is LoginContract.Event.Submit) {
                        buttonClicked = true
                    }
                },
                connectState = ConnectivityObserver.Status.Available,
                snackbarHostState = SnackbarHostState(),
                loading = false
            )
        }

        composeTestRule.onNodeWithText("ВХОД").performClick()

        assert(buttonClicked)
    }

    @Test
    fun internetConnectionStatusDisplayed() {
        composeTestRule.setContent {
            LoginScreen(
                state = LoginContract.State(),
                isForbidden = false,
                onEventSent = {},
                ConnectivityObserver.Status.Unavailable, // Simulating no internet
                SnackbarHostState(),
                loading = false
            )
        }

        // Verify if the text for no internet connection is displayed
        composeTestRule.onNodeWithText("Отсутствует подключение к интернету.")
            .assertIsDisplayed()
    }

    @Test
    fun loginErrorDisplayed() {
        composeTestRule.setContent {
            LoginScreen(
                state = LoginContract.State(loginError = AuthError.FieldEmpty),
                isForbidden = false,
                onEventSent = {},
                ConnectivityObserver.Status.Available,
                SnackbarHostState(),
                loading = false
            )
        }

        // Verify if the error message for empty login field is displayed
        composeTestRule.onNodeWithText("Введите логин").assertIsDisplayed()
    }

    @Test
    fun passwordErrorDisplayed() {
        composeTestRule.setContent {
            LoginScreen(
                state = LoginContract.State(passwordError = AuthError.FieldEmpty),
                isForbidden = false,
                onEventSent = {},
                ConnectivityObserver.Status.Available,
                SnackbarHostState(),
                loading = false
            )
        }

        // Verify if the error message for empty password field is displayed
        composeTestRule.onNodeWithText("Введите пароль").assertIsDisplayed()
    }

    @Test
    fun forbiddenErrorDisplayed() {
        composeTestRule.setContent {
            LoginScreen(
                state = LoginContract.State(),
                isForbidden = true,
                onEventSent = {},
                ConnectivityObserver.Status.Available,
                SnackbarHostState(),
                loading = false
            )
        }

        // Verify if the forbidden error message is displayed
        composeTestRule.onNodeWithText("Неверный логин или пароль").assertIsDisplayed()
    }

    @Test
    fun loadingIndicatorDisplayed() {
        composeTestRule.setContent {
            LoginScreen(
                state = LoginContract.State(),
                isForbidden = false,
                onEventSent = {},
                ConnectivityObserver.Status.Available,
                SnackbarHostState(),
                loading = true // Simulating loading state
            )
        }

        // Verify if the loading indicator is displayed
        composeTestRule.onNodeWithContentDescription("Progress Indicator").assertIsDisplayed()
    }
}
