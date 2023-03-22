import 'axios' from '../js/axios.js'

const instance = axios.create({
  baseURL: 'http://localhost:8014/',
  timeout: 1000,
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
});