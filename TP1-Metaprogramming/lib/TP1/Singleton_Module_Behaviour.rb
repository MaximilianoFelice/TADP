module TP

  module Singleton_Module_Accessors

    attr_accessor :singleton_module

    # It's pretty important to define a method_missing behaviour and NOT expect that any behaviour added to singleton module appear on it's own.
    # Ruby's linearization system makes modules respond only to a PREVIOUSLY DEFINED methods AT INCLUDE TIME. This means that,
    # when behaviour is added to modules, it doesn't impact on any previous instance including them.
    def method_missing(method, *args, &block)
      if (@singleton_module.method_defined?(method))
        method_to_execute = @singleton_module.instance_method(method)
        method_to_execute.bind(self).call(*args, &block)
      else
        super
      end
    end

    def singleton_module
      if !@singleton_module then
        @singleton_module = Module.new
        self.extend @singleton_module
      end
      @singleton_module
    end

    def define_singleton_module_method(selector, block)
      self.singleton_module.send(:define_method, selector, block)
    end

    def instance_module_variable_define(selector)
      self.singleton_module.module_eval{ attr_accessor selector }
    end

  end

  module Singleton_Module

    include Singleton_Module_Accessors

    # If the module is included by a class, it defines behaviour for every new instance AND for the class instance itself
    def self.included(including_class)
      including_class.singleton_class.include Singleton_Module_Accessors
    end

  end

end