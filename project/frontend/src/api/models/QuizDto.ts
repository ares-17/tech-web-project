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
import type { QuestionDto } from '../../open-api/models/QuestionDto';
import {
    QuestionDtoFromJSON,
    QuestionDtoFromJSONTyped,
    QuestionDtoToJSON,
} from '../../open-api/models/QuestionDto';

/**
 * 
 * @export
 * @interface QuizDto
 */
export interface QuizDto {
    /**
     * 
     * @type {string}
     * @memberof QuizDto
     */
    id?: string;
    /**
     * 
     * @type {string}
     * @memberof QuizDto
     */
    title: string;
    /**
     * 
     * @type {string}
     * @memberof QuizDto
     */
    description?: string;
    /**
     * 
     * @type {Date}
     * @memberof QuizDto
     */
    createdAt?: Date;
    /**
     * 
     * @type {string}
     * @memberof QuizDto
     */
    createdBy: string;
    /**
     * 
     * @type {number}
     * @memberof QuizDto
     */
    maxErrors: number;
    /**
     * 
     * @type {boolean}
     * @memberof QuizDto
     */
    isOpen?: boolean;
    /**
     * 
     * @type {Array<QuestionDto>}
     * @memberof QuizDto
     */
    questions: Array<QuestionDto>;
}

/**
 * Check if a given object implements the QuizDto interface.
 */
export function instanceOfQuizDto(value: object): value is QuizDto {
    if (!('title' in value) || value['title'] === undefined) return false;
    if (!('createdBy' in value) || value['createdBy'] === undefined) return false;
    if (!('maxErrors' in value) || value['maxErrors'] === undefined) return false;
    if (!('questions' in value) || value['questions'] === undefined) return false;
    return true;
}

export function QuizDtoFromJSON(json: any): QuizDto {
    return QuizDtoFromJSONTyped(json, false);
}

export function QuizDtoFromJSONTyped(json: any, ignoreDiscriminator: boolean): QuizDto {
    if (json == null) {
        return json;
    }
    return {
        
        'id': json['id'] == null ? undefined : json['id'],
        'title': json['title'],
        'description': json['description'] == null ? undefined : json['description'],
        'createdAt': json['createdAt'] == null ? undefined : (new Date(json['createdAt'])),
        'createdBy': json['createdBy'],
        'maxErrors': json['maxErrors'],
        'isOpen': json['isOpen'] == null ? undefined : json['isOpen'],
        'questions': ((json['questions'] as Array<any>).map(QuestionDtoFromJSON)),
    };
}

export function QuizDtoToJSON(value?: QuizDto | null): any {
    if (value == null) {
        return value;
    }
    return {
        
        'id': value['id'],
        'title': value['title'],
        'description': value['description'],
        'createdAt': value['createdAt'] == null ? undefined : ((value['createdAt']).toISOString()),
        'createdBy': value['createdBy'],
        'maxErrors': value['maxErrors'],
        'isOpen': value['isOpen'],
        'questions': ((value['questions'] as Array<any>).map(QuestionDtoToJSON)),
    };
}

