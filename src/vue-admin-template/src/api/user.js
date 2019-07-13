import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/doctor/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/doctor/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/doctor/logout',
    method: 'post'
  })
}
