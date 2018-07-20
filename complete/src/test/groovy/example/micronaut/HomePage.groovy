package example.micronaut

import geb.Page

class HomePage extends Page {

    static url = '/static/index.html'

    static at = { title == 'Micronaut Guide' }

    static content = {
        usernameInput { $('input#username')}
        passwordInput { $('input#password')}
        logoutButton { $('#logoutButton') }
        loginButton { $('#loginSubmitBtn') }
        registerButton { $('#registerSubmitBtn') }
        loginFormErrorsDiv { $('#loginFormErrors') }
        registerFormErrorsDiv { $('#registerFormErrors') }
    }

    String loginMessage() {
        loginFormErrorsDiv.text()
    }

    String registerMessage() {
        registerFormErrorsDiv.text()
    }

    void logout() {
        logoutButton.click()
    }

    void register(String email, String password) {
        usernameInput = email
        passwordInput = password
        registerButton.click()
    }

    void login(String email, String password) {
        usernameInput = email
        passwordInput = password
        loginButton.click()    }

}
