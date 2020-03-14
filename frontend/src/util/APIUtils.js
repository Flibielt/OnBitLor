import {API_BASE_URL, POLL_LIST_SIZE, ACCESS_TOKEN, PROGRAMMING_LIST_SIZE} from '../constants';

const request = async (options) => {
    const headers = new Headers({
        'Content-Type': 'application/json',
    });
    
    if(localStorage.getItem(ACCESS_TOKEN)) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
    }

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    const response = await fetch(options.url, options);
    const json = await response.json();
    if (!response.ok) {
        return Promise.reject(json);
    }
    return json;
};

export function getAllProgrammings(page, size) {
    page = page || 0;
    size = size || PROGRAMMING_LIST_SIZE;

    return request({
        url: API_BASE_URL + "/programming/all?page=" + page + "&size=" + size,
        method: 'GET'
    });
}

export function addNewProgramming(programming) {
    return request({
        url: API_BASE_URL + "/programming",
        method: 'POST',
        body: programming
    })
}

export function addProgrammingResult(resultData) {
    console.log(JSON.stringify(resultData));
    return request({
        url: API_BASE_URL + "/programming/addResult",
        method: 'POST',
        body: JSON.stringify(resultData)
    })
}

export function checkProgrammingNameAvailability(name) {
    return request({
        url: API_BASE_URL + "/programming/checkNameAvailability?name=" + name,
        method: 'GET'
    })
}

export function getAllProgrammingResult(programmingName) {
    return request({
        url: API_BASE_URL + "/programming/results?programming=" + programmingName,
        method: 'GET'
    })
}

export function getAllTests() {
    return request({
        url: API_BASE_URL + "/test/all",
        method: 'GET'
    });
}

export function addNewTest(test) {
    return request({
        url: API_BASE_URL + "/test/new",
        method: 'POST',
        body: test
    })
}

export function addTestResult(resultData) {
    return request({
        url: API_BASE_URL + "/test",
        method: 'POST',
        body: JSON.stringify(resultData)
    })
}

export function checkTestNameAvailability(name) {
    return request({
        url: API_BASE_URL + "/test/checkNameAvailability?name=" + name,
        method: 'GET'
    })
}

export function getAllTestResult(programmingName) {
    return request({
        url: API_BASE_URL + "/test/results?name=" + programmingName,
        method: 'GET'
    })
}

export function getAllGames() {
    return request({
        url: API_BASE_URL + "/game/all",
        method: 'GET'
    })
}

export function getAllPolls(page, size) {
    page = page || 0;
    size = size || POLL_LIST_SIZE;

    return request({
        url: API_BASE_URL + "/polls?page=" + page + "&size=" + size,
        method: 'GET'
    });
}

export function createPoll(pollData) {
    return request({
        url: API_BASE_URL + "/polls",
        method: 'POST',
        body: JSON.stringify(pollData)         
    });
}

export function castVote(voteData) {
    return request({
        url: API_BASE_URL + "/polls/" + voteData.pollId + "/votes",
        method: 'POST',
        body: JSON.stringify(voteData)
    });
}

export function login(loginRequest) {
    return request({
        url: API_BASE_URL + "/auth/signin",
        method: 'POST',
        body: JSON.stringify(loginRequest)
    });
}

export function signup(signupRequest) {
    return request({
        url: API_BASE_URL + "/auth/signup",
        method: 'POST',
        body: JSON.stringify(signupRequest)
    });
}

export function checkUsernameAvailability(username) {
    return request({
        url: API_BASE_URL + "/user/checkUsernameAvailability?username=" + username,
        method: 'GET'
    });
}

export function checkEmailAvailability(email) {
    return request({
        url: API_BASE_URL + "/user/checkEmailAvailability?email=" + email,
        method: 'GET'
    });
}


export function getCurrentUser() {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        console.log("No acces token set.");
        return Promise.reject("No access token set.");
    }
    console.log("Access token set.");

    return request({
        url: API_BASE_URL + "/player/me",
        method: 'GET'
    });
}

export function getUserProfile(username) {
    return request({
        url: API_BASE_URL + "/player/" + username,
        method: 'GET'
    });
}

export function getUserCreatedPolls(username, page, size) {
    page = page || 0;
    size = size || POLL_LIST_SIZE;

    return request({
        url: API_BASE_URL + "/users/" + username + "/polls?page=" + page + "&size=" + size,
        method: 'GET'
    });
}

export function getUserVotedPolls(username, page, size) {
    page = page || 0;
    size = size || POLL_LIST_SIZE;

    return request({
        url: API_BASE_URL + "/users/" + username + "/votes?page=" + page + "&size=" + size,
        method: 'GET'
    });
}
