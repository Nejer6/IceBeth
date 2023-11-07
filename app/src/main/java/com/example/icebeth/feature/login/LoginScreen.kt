package com.example.icebeth.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.icebeth.common.presentation.theme.IceBethTheme
import com.example.icebeth.common.presentation.theme.spacing
import com.example.icebeth.core.data.util.ConnectivityObserver
import com.example.icebeth.core.domain.util.AuthError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    state: LoginContract.State,
    isForbidden: Boolean,
    onEventSent: (LoginContract.Event) -> Unit,
    connectState: ConnectivityObserver.Status,
    snackbarHostState: SnackbarHostState,
    loading: Boolean
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (connectState != ConnectivityObserver.Status.Available) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.error)
                        .padding(MaterialTheme.spacing.medium)
                ) {
                    Text(
                        text = "Отсутствует подключение к интернету.",
                        color = MaterialTheme.colorScheme.onError
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.large)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Добро пожаловать!",
                    style = MaterialTheme.typography.displayMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

                if (isForbidden) {
                    Text(
                        text = "Неверный логин или пароль",
                        color = MaterialTheme.colorScheme.error
                    )
                }

                TextField(
                    value = state.login,
                    onValueChange = { onEventSent(LoginContract.Event.SetLogin(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Логин")
                    },
                    isError = state.loginError != null,
                    supportingText = {
                        if (state.loginError != null) {
                            Text(
                                text = when (state.loginError) {
                                    AuthError.FieldEmpty -> "Введите логин"
                                },
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                var isPasswordShow by remember {
                    mutableStateOf(false)
                }
                TextField(
                    value = state.password,
                    onValueChange = { onEventSent(LoginContract.Event.SetPassword(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Пароль")
                    },
                    trailingIcon = {
                        if (isPasswordShow) {
                            IconButton(onClick = { isPasswordShow = false }) {
                                Icon(
                                    imageVector = Icons.Default.VisibilityOff,
                                    contentDescription = "Скрыть пароль"
                                )
                            }
                        } else {
                            IconButton(onClick = { isPasswordShow = true }) {
                                Icon(
                                    imageVector = Icons.Default.Visibility,
                                    contentDescription = "Показать пароль"
                                )
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = if (isPasswordShow) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    isError = state.passwordError != null,
                    supportingText = {
                        if (state.passwordError != null) {
                            Text(
                                text = when (state.passwordError) {
                                    AuthError.FieldEmpty -> "Введите пароль"
                                },
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                Button(
                    onClick = { onEventSent(LoginContract.Event.Submit) },
                    enabled = (connectState == ConnectivityObserver.Status.Available) && !loading
                ) {
                    if (loading) {
                        Box(
                            modifier = Modifier
                                .width(38.dp)
                                .height(19.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .width(19.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    } else {
                        Text(text = "ВХОД")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    IceBethTheme(darkTheme = true) {
        LoginScreen(
            state = LoginContract.State(),
            isForbidden = false,
            onEventSent = {},
            ConnectivityObserver.Status.Available,
            SnackbarHostState(),
            true
        )
    }
}
