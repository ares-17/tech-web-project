import CryptoJS from 'crypto-js';
import { defineStore } from 'pinia';
import { BehaviorSubject } from 'rxjs';

export const useSessionStore = defineStore('sessionStore', () => {

    const sessionStorageSubject = new BehaviorSubject(getAllSessionStorage());
    const isLoggedSubject = new BehaviorSubject(isLoggedIn(getAllSessionStorage()));

    function isLoggedIn(store: any): boolean {
        return !!store.token && !!store.expiresIn;
    }

    function saveToSessionStorage(key: string, value: string){
        const secretKey = import.meta.env.VITE_SECRET_KEY;
        if (!secretKey) {
          throw new Error('VITE_SECRET_KEY is not defined');
        }
        const encryptedVal = CryptoJS.AES.encrypt(value, secretKey).toString();
        sessionStorage.setItem(key, encryptedVal);
        sessionStorageSubject.next(getAllSessionStorage());
        isLoggedSubject.next(isLoggedIn(getAllSessionStorage()));
    }

    function getFromSessionStorage(key: string){
        const secretKey = import.meta.env.VITE_SECRET_KEY;
        if (!secretKey) {
          throw new Error('VITE_SECRET_KEY is not defined');
        }
        const encryptedVal = sessionStorage.getItem(key);
        if (encryptedVal) {
          const bytes = CryptoJS.AES.decrypt(encryptedVal, secretKey);
          const decryptedToken = bytes.toString(CryptoJS.enc.Utf8);
          return decryptedToken;
        }
        return null;
    }

    function removeSessionStorageItem(key: string) {
        sessionStorage.removeItem(key);
        sessionStorageSubject.next(getAllSessionStorage());
        isLoggedSubject.next(isLoggedIn(getAllSessionStorage()));
      }

    function getAllSessionStorage() {
        let store: any = {};
        for (let i = 0; i < sessionStorage.length; i++) {
          const key: any = sessionStorage.key(i);
          store[key] = sessionStorage.getItem(key);
        }
        return store;
    }

    return {
        saveToSessionStorage,
        getFromSessionStorage,
        removeSessionStorageItem,
        isLoggedSubject,
        sessionStorageSubject
    };
});
