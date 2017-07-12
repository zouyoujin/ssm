/**
 * Returns whether the provided value is a promise
 *
 * @param {object} value Potential promise
 * @return {Boolean}
 */
export function isPromise(value) {
    if (value !== null && typeof value === 'object') {
        return value.promise && typeof value.promise.then === 'function';
    }else{
        return value;
    }
}

export function getCookie() {
    let value = document.cookie;
    let parts = value.split(";");
    let obj = {};
    for(let i=0;i<parts.length;i++){
        let pair = parts[i].split("=");
        if(pair.length==2)
            obj[pair[0].trim()]=pair[1].trim();
    }
    return obj;
}
