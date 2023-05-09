//
//  Helper.swift
//  ios
//
//  Created by Choesang Tenzin on 26.02.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit


extension Bundle {
    
    
    func decode<T: Decodable>(_ type: T.Type, from file: String) -> T {
        guard let url = self.url(forResource: file, withExtension: "json") else {
            fatalError("Failed to locate \(file) in bundle.")
        }

        guard let data = try? Data(contentsOf: url) else {
            fatalError("Failed to load \(file) from bundle.")
        }

        let decoder = JSONDecoder()

        guard let loaded = try? decoder.decode(T.self, from: data) else {
            fatalError("Failed to decode \(file) from bundle.")
        }

        return loaded
    }
    
    func dateFromString(date: String) -> Date {
        let dateFormatter = ISO8601DateFormatter()
        dateFormatter.formatOptions = [.withFullDate] // Added format options
        let formattedDate = dateFormatter.date(from: date) ?? Date.now
        return formattedDate;
    }
    
    func userVisibleDate(date: String) -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateStyle = .medium
        dateFormatter.timeStyle = .none
        dateFormatter.locale = Locale(identifier: "de_CH")
        return dateFormatter.string(from: dateFromString(date: date))
    }
    
    func convertToDouble(resultingSaldo: String) -> Double {
        print(resultingSaldo)
        return Double(resultingSaldo) ?? 0.0;
    }
}
