import React, { createContext, useContext } from 'react';

const PersonalIdContext = createContext();

export const PersonalIdProvider = ({ children }) => {
    const personalId = 1; // Atur ID di sini

    return (
        <PersonalIdContext.Provider value={personalId}>
            {children}
        </PersonalIdContext.Provider>
    );
};

export const usePersonalId = () => {
    return useContext(PersonalIdContext);
};