import jwtDecode from 'jwt-decode';
import config from '../../config';

export function getToken() {
  return localStorage.getItem(config.AUTH_TOKEN_KEY);
}

export function setToken(token) {
  localStorage.setItem(config.AUTH_TOKEN_KEY, token);
}

export function setUserInfo() {
  const token = getToken();
  const tokenData = jwtDecode(token);
  const userType = tokenData.authorities[0];
  localStorage.setItem("type", userType);
}

export function getUserType() {
  return localStorage.getItem("type")
}

export function logout() {
  localStorage.clear();
}

export function validateJWT(token) {
  try {
    const tokenData = jwtDecode(token);
    return Date.now() < tokenData.exp * 1000;
  } catch (e) {
    return false;
  }
}