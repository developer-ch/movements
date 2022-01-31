
export type Vehicle = {
    id: number;
    plaqueOne: string;
    plaqueTwo: string;
}

export type VehiclePage = {
    content?: Vehicle[];
    last: boolean;
    totalElements: number;
    totalPages: number;
    size?: number;
    number: number;
    first: boolean;
    numberOfElements?: number;
    empty?: boolean;     
}