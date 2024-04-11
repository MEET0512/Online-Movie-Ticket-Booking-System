import { CommonModule } from '@angular/common';
import { Component, Input, forwardRef, input } from '@angular/core';
import { ControlValueAccessor, FormsModule, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-input',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './input.component.html',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => InputComponent),
      multi: true,
    }
  ]
})
export class InputComponent implements ControlValueAccessor {

  @Input() label = '';
  @Input() type = 'text';
  @Input() name = '';
  @Input() password = false;

  value: any = '';
  isFocused = false;
  isShow = false;
  onChange: any = () => {};
  onTouched: any = () => {};

  onFocus() {
    this.isFocused = true;
  }

  onBlure() {
    if(!this.value) {
      this.isFocused = false;
    }
  }

  writeValue(value: any) {
    this.value = value;
  }

  registerOnChange(fn: any) {
    this.onChange = fn;
  }

  registerOnTouched(fn: any) {
    this.onTouched = fn;
  }

  togglePassword() {
    this.isShow = !this.isShow;
    if(this.type === 'password'){
      this.type = 'text';
    } else {
      this.type = 'password';
    }
  }
}
