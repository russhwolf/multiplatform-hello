//
//  ViewController.swift
//  Multiplatform Hello
//
//  Created by Russell Wolf on 9/26/19.
//  Copyright © 2019 Kotlin/Native. All rights reserved.
//

import UIKit
import Shared

class ViewController: UIViewController {

    @IBOutlet var label: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        HelloKt.hello { message in
            self.label.text = message
        }
    }


}

