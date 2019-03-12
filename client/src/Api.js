import axios from 'axios'

const SERVER_URL = 'http://localhost:9000'

const instance  = axios.create({
    baseURL: SERVER_URL,
    timeout: 1000
})

export default {
    //(C)reate
    createNew: (text) => instance.post('todos', {title: text}),

    // (R)ead
    getAll: () => instance.get("todos"),
    removeForId: (id) => instance.delete("todos/" + id),
    updateForId: (id, text, completed) => instance.put("todos/" + id, {title:text, completed: completed})
}