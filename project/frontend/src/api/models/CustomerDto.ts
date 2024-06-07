/* tslint:disable */
/* eslint-disable */
/**
 * Tech Web - OpenAPI 3.0
 * Prova
 *
 * The version of the OpenAPI document: 1.0.1
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { mapValues } from './runtime';
/**
 * 
 * @export
 * @interface CustomerDto
 */
export interface CustomerDto {
    /**
     * 
     * @type {string}
     * @memberof CustomerDto
     */
    id?: string;
    /**
     * 
     * @type {string}
     * @memberof CustomerDto
     */
    username: string;
    /**
     * 
     * @type {boolean}
     * @memberof CustomerDto
     */
    isLogged?: boolean;
    /**
     * 
     * @type {boolean}
     * @memberof CustomerDto
     */
    isAnonymous?: boolean;
    /**
     * 
     * @type {string}
     * @memberof CustomerDto
     */
    password: string;
}

/**
 * Check if a given object implements the CustomerDto interface.
 */
export function instanceOfCustomerDto(value: object): value is CustomerDto {
    if (!('username' in value) || value['username'] === undefined) return false;
    if (!('password' in value) || value['password'] === undefined) return false;
    return true;
}

export function CustomerDtoFromJSON(json: any): CustomerDto {
    return CustomerDtoFromJSONTyped(json, false);
}

export function CustomerDtoFromJSONTyped(json: any, ignoreDiscriminator: boolean): CustomerDto {
    if (json == null) {
        return json;
    }
    return {
        
        'id': json['id'] == null ? undefined : json['id'],
        'username': json['username'],
        'isLogged': json['isLogged'] == null ? undefined : json['isLogged'],
        'isAnonymous': json['isAnonymous'] == null ? undefined : json['isAnonymous'],
        'password': json['password'],
    };
}

export function CustomerDtoToJSON(value?: CustomerDto | null): any {
    if (value == null) {
        return value;
    }
    return {
        
        'id': value['id'],
        'username': value['username'],
        'isLogged': value['isLogged'],
        'isAnonymous': value['isAnonymous'],
        'password': value['password'],
    };
}

