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

import { mapValues } from '../../open-api/runtime';
/**
 * 
 * @export
 * @interface AuthenticationDto
 */
export interface AuthenticationDto {
    /**
     * 
     * @type {number}
     * @memberof AuthenticationDto
     */
    expiresIn?: number;
    /**
     * 
     * @type {string}
     * @memberof AuthenticationDto
     */
    token?: string;
    /**
     * 
     * @type {string}
     * @memberof AuthenticationDto
     */
    idCustomer?: string;
}

/**
 * Check if a given object implements the AuthenticationDto interface.
 */
export function instanceOfAuthenticationDto(value: object): value is AuthenticationDto {
    return true;
}

export function AuthenticationDtoFromJSON(json: any): AuthenticationDto {
    return AuthenticationDtoFromJSONTyped(json, false);
}

export function AuthenticationDtoFromJSONTyped(json: any, ignoreDiscriminator: boolean): AuthenticationDto {
    if (json == null) {
        return json;
    }
    return {
        
        'expiresIn': json['expiresIn'] == null ? undefined : json['expiresIn'],
        'token': json['token'] == null ? undefined : json['token'],
        'idCustomer': json['idCustomer'] == null ? undefined : json['idCustomer'],
    };
}

export function AuthenticationDtoToJSON(value?: AuthenticationDto | null): any {
    if (value == null) {
        return value;
    }
    return {
        
        'expiresIn': value['expiresIn'],
        'token': value['token'],
        'idCustomer': value['idCustomer'],
    };
}

