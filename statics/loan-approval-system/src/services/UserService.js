import axios from 'axios';

const USER_API_BASE_URL = "";

class UserService {

    getEmployees(){
        return axios.get(USER_API_BASE_URL);
    }

    createEmployee(employee){
        return axios.post(USER_API_BASE_URL, user);
    }

}

export default new UserService()