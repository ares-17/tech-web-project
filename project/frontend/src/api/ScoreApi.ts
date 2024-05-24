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


import * as runtime from './models/runtime';
import type {
  Problem,
  ScoreDto,
} from './models/index';
import {
    ProblemFromJSON,
    ProblemToJSON,
    ScoreDtoFromJSON,
    ScoreDtoToJSON,
} from './models/index';

export interface CompleteQuizRequest {
    scoreDto: ScoreDto;
}

export interface GetScoreByCustomerRequest {
    idCustomer: string;
}

export interface GetScoresByQuizRequest {
    idQuiz: string;
}

/**
 * 
 */
export class ScoreApi extends runtime.BaseAPI {

    /**
     * Associa il punteggio ottenuto dall\'utente
     */
    async completeQuizRaw(requestParameters: CompleteQuizRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<void>> {
        if (requestParameters['scoreDto'] == null) {
            throw new runtime.RequiredError(
                'scoreDto',
                'Required parameter "scoreDto" was null or undefined when calling completeQuiz().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/score/complete`,
            method: 'POST',
            headers: headerParameters,
            query: queryParameters,
            body: ScoreDtoToJSON(requestParameters['scoreDto']),
        }, initOverrides);

        return new runtime.VoidApiResponse(response);
    }

    /**
     * Associa il punteggio ottenuto dall\'utente
     */
    async completeQuiz(requestParameters: CompleteQuizRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<void> {
        await this.completeQuizRaw(requestParameters, initOverrides);
    }

    /**
     * Restituisce i punteggi ottenuti da un singolo giocatore
     */
    async getScoreByCustomerRaw(requestParameters: GetScoreByCustomerRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<Array<ScoreDto>>> {
        if (requestParameters['idCustomer'] == null) {
            throw new runtime.RequiredError(
                'idCustomer',
                'Required parameter "idCustomer" was null or undefined when calling getScoreByCustomer().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/score/quiz/{idCustomer}`.replace(`{${"idCustomer"}}`, encodeURIComponent(String(requestParameters['idCustomer']))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => jsonValue.map(ScoreDtoFromJSON));
    }

    /**
     * Restituisce i punteggi ottenuti da un singolo giocatore
     */
    async getScoreByCustomer(requestParameters: GetScoreByCustomerRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<Array<ScoreDto>> {
        const response = await this.getScoreByCustomerRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Restituisce i punteggi di tutti i giocatori che hanno sostenuto un quiz
     */
    async getScoresByQuizRaw(requestParameters: GetScoresByQuizRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<Array<ScoreDto>>> {
        if (requestParameters['idQuiz'] == null) {
            throw new runtime.RequiredError(
                'idQuiz',
                'Required parameter "idQuiz" was null or undefined when calling getScoresByQuiz().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/score/customers/{idQuiz}`.replace(`{${"idQuiz"}}`, encodeURIComponent(String(requestParameters['idQuiz']))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => jsonValue.map(ScoreDtoFromJSON));
    }

    /**
     * Restituisce i punteggi di tutti i giocatori che hanno sostenuto un quiz
     */
    async getScoresByQuiz(requestParameters: GetScoresByQuizRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<Array<ScoreDto>> {
        const response = await this.getScoresByQuizRaw(requestParameters, initOverrides);
        return await response.value();
    }

}
