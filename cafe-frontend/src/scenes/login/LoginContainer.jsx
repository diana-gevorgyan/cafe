import React, {Component} from 'react';
import LoginForm from "./LoginForm";
import history from "../../utils/history";
import {login} from "../../client/BackendApiClient";
import {getUserType, setToken, setUserInfo} from "../../utils/auth";

class LoginContainer extends Component {
  constructor(props, context) {
    super(props, context);
    this.state = {
      email: "",
      password: "",
      errors: {
        password: false,
        email: false,
        other: '',
      },
    }
    this.setEmail = this.setEmail.bind(this);
    this.setPassword = this.setPassword.bind(this);
    this.tryLogin = this.tryLogin.bind(this);
    this.getJwtToken = this.getJwtToken.bind(this);
  }

  setEmail(email) {
    this.setState({email: email})
  }

  setPassword(password) {
    this.setState({password: password})
  }

  getJwtToken = (response) => (
    response.headers["jwt-auth-token"]
  );

  tryLogin() {
    const {email, password} = this.state;

    if (email === '') {
      this.setState({
        errors: {
          password: true,
          email: true,
        },
      });
      return;
    }
    if (password === '') {
      this.setState({
        errors: {
          password: true,
          email: false,
        },
      });
      return;
    }

    login(email, password)
      .then((res) => {
        const token = this.getJwtToken(res);
        setToken(token);
        setUserInfo();
      })
      .catch((error) => {
        const {response: {status, data}} = error;
        if (status === 401 || status === 403) {
          this.setState({
            errors: {
              email: true,
              password: true,
              other: data,
            },
          });
        }
      })
      .finally(() => {
          if (getUserType() === 'MANAGER') {
            history.push('/manager');
          }
          if (getUserType() === 'WAITER') {
            history.push('/waiter');
          }
        }
      )
  }

  render() {
    const {email, password, errors} = this.state;
    return (
      <>
        <LoginForm
          email={email}
          setEmail={this.setEmail}
          password={password}
          setPassword={this.setPassword}
          tryLogin={this.tryLogin}
          errors={errors}
        />
      </>
    );
  }
}

LoginContainer.propTypes = {};

export default LoginContainer;
