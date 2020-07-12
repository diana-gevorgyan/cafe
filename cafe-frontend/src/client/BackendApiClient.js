import axios from 'axios';
import config from "../config";
import {getToken} from "../utils/auth";

const {BACKEND_URL} = config;

export function login(email, password) {
  console.log(BACKEND_URL)
  return axios({
    withCredentials: true,
    method: 'POST',
    url: `${BACKEND_URL}/login`,
    data: {
      email,
      password,
    },
  });
}

export function register(email, password, name, type) {
  return axios({
    method: 'POST',
    url: `${BACKEND_URL}/auth/register`,
    data: {
      email,
      password,
      name,
      type,
    },
  });
}

export function doAuthorizedRequest(data) {
  const defaultHeaders = {
    'X-Requested-With': 'XMLHttpRequest',
    'jwt-auth-token': getToken(),
  };

  const resultData = {...data, headers: {...data.headers, ...defaultHeaders}};
  return axios(resultData);
}

export function getAllTables() {
  return doAuthorizedRequest({
    method: 'GET',
    url: `${BACKEND_URL}/tables/all`,
  });
}

export function registerNewTable(number, waiterId) {
  return doAuthorizedRequest({
    method: 'POST',
    url: `${BACKEND_URL}/tables/create`,
    data: {
      number,
      waiterId
    },
  });
}

export function changeTableAssignee(tableId, waiteId) {
  return doAuthorizedRequest({
    method: 'PUT',
    url: `${BACKEND_URL}/tables/${tableId}/assign/${waiteId}`
  })
}

export function getAllWaitersList() {
  return doAuthorizedRequest({
    method: 'GET',
    url: `${BACKEND_URL}/users/waiters`
  });
}

export function getAllProducts() {
  return doAuthorizedRequest({
    method: 'GET',
    url: `${BACKEND_URL}/products/all`
  });
}

export function createNewProduct(name, price) {
  return doAuthorizedRequest({
    method: 'POST',
    url: `${BACKEND_URL}/products/create`,
    data: {
      name,
      price
    },
  });
}

export function getAllUsers() {
  return doAuthorizedRequest({
    method: 'GET',
    url: `${BACKEND_URL}/users/all`
  })
}

export function createNewUser(name, email, password, type) {
  return doAuthorizedRequest({
    method: 'POST',
    url: `${BACKEND_URL}/users/register`,
    data: {
      name,
      password,
      email,
      type
    },
  });
}
