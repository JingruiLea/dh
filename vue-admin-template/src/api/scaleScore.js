import request from '@/utils/request'

const prefix = 'scale'

export function selectAll() {
  return get("selectAll")
}
export function insert(doctor) {
  return post("insert", doctor)
}
export function deleteByPrimaryKey(id) {
  return get("deleteByPrimaryKey", {id})
}
export function updateByPrimaryKey(doctor) {
  return post("updateByPrimaryKey", doctor)
}
export function selectByPrimaryKey(id) {
  return get("selectByPrimaryKey", {id})
}

export function selectByPatientId(id) {
  return get("selectByPatientId", {id})
}

function post(subUrl, data) {
  return request({
    url: `/${prefix}/${subUrl}`,
    method: 'post',
    data
  })
}

function get(subUrl, params) {
  return request({
    url: `/${prefix}/${subUrl}`,
    method: 'get',
    params
  })
}
